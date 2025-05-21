# Pokedex Compose

> A modern Android app built with **Jetpack Compose** and powered by the [PokeAPI](https://pokeapi.co/).

![Group 1948760198](https://github.com/user-attachments/assets/d6dc6687-66cf-4d18-8387-e0b0ac98fcd5)


---

## ✨ Features

* 🧩 Paginated list of Pokémon
* 📄 Detailed Pokémon information
* ⭐ Favorite and unfavorite Pokémon

---

## 🛠️ Tech Stack

| Category             | Technology                             |
| -------------------- | -------------------------------------- |
| Language             | Kotlin                                 |
| Architecture         | Clean Architecture + MVVM              |
| UI Framework         | Jetpack Compose                        |
| Dependency Injection | Hilt                                   |
| Networking           | Retrofit + kotlinx.serialization       |
| Image Loading        | Coil                                   |
| Async & Stream       | Kotlin Coroutines + Flow               |
| UI Effects           | Compose Shimmer, AnimatedVisibility... |

---

## 🧱 Project Architecture

This project follows a **modular Clean Architecture** approach combined with **MVVM**.

### Layer Overview:

* **Presentation Layer**

  * UI, ViewModels, UI state & side effects
  * Built using Jetpack Compose

* **Domain Layer**

  * Platform-agnostic core logic
  * Contains use cases, interfaces, and models

* **Data Layer**

  * Implements domain interfaces
  * Handles API calls, local storage, data mapping

* **Core Layer**

  * Shared modules such as theme, network config, and navigation

```
app
├── core
│   ├── designsystem       
│   ├── model              
│   ├── navigation         
│   └── network            
│
├── data
│   ├── datasource         
│   ├── di                 
│   ├── model.response     
│   ├── repositoryimpl     
│   ├── service            
│   ├── storage            # Room database and local caching
│   │   ├── di
│   │   ├── database
│   │   ├── entity
│   │   └── datasource
│   └── utils              # API utils, mappers, extensions
│
├── domain
│   ├── model              
│   ├── repository         
│   └── usecase            
│
└── presentation
    ├── base               
    ├── detail             
    ├── favorite           
    ├── list               
    ├── main               
    └── utils              
```

---

## 📦 Inspired By

* [skydoves/Pokedex](https://github.com/skydoves/Pokedex)
* [mrcsxsiq/Kotlin-Pokedex](https://github.com/mrcsxsiq/Kotlin-Pokedex)
* [MohamedRejeb/Pokedex](https://github.com/MohamedRejeb/Pokedex)


---

## 🔗 License

This project is under the MIT License.

---

Thanks for visiting! 👋 Feel free to contribute or suggest improvements!
