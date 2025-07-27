## Technical details

The Application implemented and structured based on the **MVVM** pattern.

The **Data** layer contains **Network Client** implemented by *Retrofit* library to get access to remote data on [{JSON} Placeholder API](https://https://jsonplaceholder.typicode.com/) and **DB** implemented by *Room* library to persist those data locally.

The **View** layer is done with the [Android Navigation Component](https://developer.android.com/guide/navigation) including one MainActivity which holds the navigation host fragment.

The **Koin** library does the *dependency injections* in the whole app.

## Libraries

- **Retrofit** and **OkHttp** API libraries made by [square](https://github.com/square/retrofit)
- **Kotlin Serialization** plugin made by [jetbrains](https://github.com/Kotlin/kotlinx.serialization)
- **Koin** dependency injector library made by [InsertKoinIO](https://github.com/InsertKoinIO/koin)

## License

Copyright 2025 Alexander de Oliveira

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0