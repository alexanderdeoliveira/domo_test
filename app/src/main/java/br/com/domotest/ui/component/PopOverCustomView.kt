package br.com.domotest.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.domotest.R
import br.com.domotest.databinding.ViewPopOverCustomBinding

class PopOverCustomView: ConstraintLayout {

    private var binding: ViewPopOverCustomBinding = ViewPopOverCustomBinding.inflate(LayoutInflater.from(context))
    private var _type = PopOverCustomViewType.DATE_AND_TIME
    private var _dayDefault: Int = 0
    private var _monthDefault: Int? = null
    private var _yearDefault: Int? = null
    private var _hourDefault: Int? = null
    private var _minuteDefault: Int? = null

    private var type: PopOverCustomViewType
        get() = _type
        set(value) {
            _type = value
        }

    private var dayDefault: Int
        get() = _dayDefault
        set(value) {
            _dayDefault = value
        }

    private var monthDefault: Int?
        get() = _monthDefault
        set(value) {
            _monthDefault = value
        }

    private var yearDefault: Int?
        get() = _yearDefault
        set(value) {
            _yearDefault = value
        }

    private var hourDefault: Int?
        get() = _hourDefault
        set(value) {
            _hourDefault = value
        }

    private var minuteDefault: Int?
        get() = _minuteDefault
        set(value) {
            _minuteDefault = value
        }

    enum class PopOverCustomViewType(val type: Int) {
        DATE_AND_TIME(0),
        ONLY_DATE(1),
        ONLY_TIME(2);

        companion object {
            fun from(type: Int) = entries.firstOrNull { type == it.type } ?: DATE_AND_TIME
        }
    }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        addView(binding.root)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PopOverCustomView, defStyle, 0)
        _type = PopOverCustomViewType.from(typedArray.getInt(R.styleable.PopOverCustomView_popupOverType, PopOverCustomViewType.DATE_AND_TIME.type))
        _dayDefault = typedArray.getInt(R.styleable.PopOverCustomView_dayDefault, 0)
        _monthDefault = typedArray.getInt(R.styleable.PopOverCustomView_monthDefault, 0)
        _yearDefault = typedArray.getInt(R.styleable.PopOverCustomView_yearDefault, 0)
        _hourDefault = typedArray.getInt(R.styleable.PopOverCustomView_hourDefault, 0)
        _minuteDefault = typedArray.getInt(R.styleable.PopOverCustomView_minuteDefault, 0)
        typedArray.recycle()

        setDefaultValues()
        validateVisibility()
    }

    private fun setDefaultValues() {
        binding.datePicker.init(yearDefault ?: 0, monthDefault ?: 0, dayDefault, null)
        binding.timePicker.hour = hourDefault ?: 0
        binding.timePicker.minute = minuteDefault ?: 0
    }

    private fun validateVisibility() {
        when (type) {
            PopOverCustomViewType.DATE_AND_TIME -> {
                binding.datePicker.visibility = View.VISIBLE
                binding.timePicker.visibility = View.VISIBLE
            }
            PopOverCustomViewType.ONLY_DATE -> {
                binding.datePicker.visibility = View.VISIBLE
                binding.timePicker.visibility = View.GONE
            }
            PopOverCustomViewType.ONLY_TIME -> {
                binding.datePicker.visibility = View.GONE
                binding.timePicker.visibility = View.VISIBLE
            }
        }
    }

    fun setOnDateChangedListener(listener: (year: Int?, month: Int?, dayOfMonth: Int?) -> Unit) {
        if (type != PopOverCustomViewType.ONLY_TIME)  {
            binding.datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
                listener(year, monthOfYear, dayOfMonth)
            }
        } else {
            listener(null, null, null)
        }
    }

    fun setOnTimeChangedListener(listener: (hourOfDay: Int?, minute: Int?) -> Unit) {
        if (type != PopOverCustomViewType.ONLY_DATE) {
            binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                listener(hourOfDay, minute)
            }
        } else {
            listener(null, null)
        }
    }

}