package br.com.domotest.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.savedstate.SavedState
import br.com.domotest.R
import br.com.domotest.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        getUserId()
        setupObservers()
    }

    private fun logout() {
        mainViewModel.logout()
    }

    private fun getUserId() {
        mainViewModel.getUserId()
    }

    private fun setupObservers() {
        mainViewModel.apply {
            userLogged.observe(this@MainActivity) { userLogged ->
                val navHostFragment = supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

                val graphInflater = navHostFragment.navController.navInflater
                val navGraph = graphInflater.inflate(R.navigation.nav_main)

                val destination = if (!userLogged) R.id.login else R.id.home
                navGraph.setStartDestination(destination)

                navController = navHostFragment.navController
                navController.addOnDestinationChangedListener(this@MainActivity)
                navController.graph = navGraph
                setupBottomNavigation()
            }
            loading.observe(this@MainActivity) { loading ->
                if (loading) {
                    binding.pbLoading.visibility = View.VISIBLE
                } else {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.apply {
            setupWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: SavedState?
    ) {
        when(destination.id) {
            R.id.login -> binding.bottomNavigation.visibility = View.GONE
            else -> binding.bottomNavigation.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        logout()
    }
}