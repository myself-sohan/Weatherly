
https://github.com/user-attachments/assets/7d366fc4-6152-4894-801b-59b3036ebef3

# Weatherly App 🌦️  

A sleek and intuitive weather application that provides real-time weather updates for cities worldwide. Built using modern Android development tools and libraries, Weatherly ensures accurate and up-to-date weather information with a clean and responsive UI.  

## ✨ Features  

- **Real-Time Weather Data** ⛅ – Fetch current weather details like temperature, humidity, wind speed, and atmospheric pressure.  
- **City Search** 🌍 – Search for any city globally to view its weather conditions.  
- **Temperature Units** 🌡️ – Switch between Celsius and Fahrenheit.  
- **Network Exception Handling** ⚠️ – Graceful error handling with messages for connectivity issues or invalid searches.  
- **Favorite Cities** ⭐ – Save and view a list of favorite cities for quick access (Room Database).  

## 🛠️ Tech Stack & Tools  

- **Kotlin** 📝 – The primary programming language for the app.  
- **Jetpack Compose** 🎨 – For building a modern, declarative UI.  
- **Retrofit** 🔗 – For making network requests to fetch weather data.  
- **Gson Converter (Retrofit)** 🔄 – To parse the JSON response from the weather API.  
- **OpenWeather API** ☁️ – To fetch live weather data for cities worldwide.  
- **Hilt (Dagger Hilt)** 🏗️ – For dependency injection and managing app-wide components.  
- **Room Database** 🗄️ – For local data storage and favorite city management.  
- **Coroutines & Flow** ⚡ – For handling asynchronous tasks and reactive data streams.  
- **MVVM Architecture** 🏛️ – For a clean separation of concerns and easier state management.  

## 🌍 API Integration  

The app integrates with the **OpenWeather API** to fetch live weather details. Retrofit is configured with a **Gson Converter** to seamlessly handle JSON parsing.  

Example API call (handled by Retrofit):  

``kotlin 
val response = api.getWeather(query = cityQuery, units = units)

## 📥 Installation  

Clone the repository:  

``bash
git clone https://github.com/yourusername/weatherly.git

## 🚀 Future Improvements  

- **Weather Forecast** 📅 – Add a 7-day weather forecast feature.  
- **Dark Mode** 🌙 – Support for dark and light themes.  
- **Location Services** 📍 – Use device GPS to fetch weather for the current location.  
- **Hourly Forecast** 🕒 – Display an hourly weather forecast with temperature, precipitation, and wind details.  
- **Weather Alerts** ⚡ – Show notifications for severe weather alerts in the user’s area.  
This is ready to go straight into your GitHub README! Let me know if you’d like me to refine it further! 🚀


![Live_Data](https://github.com/user-attachments/assets/3526e6bd-1e44-474d-b9f9-5bc9609c596a)

![Search_Screen](https://github.com/user-attachments/assets/9661746d-6e49-4b8d-b8ea-c5da227ca623)

![Home_Screen](https://github.com/user-attachments/assets/2e1c3e5d-83f0-4097-b626-3e8ce5341a62)

![Error Handling](https://github.com/user-attachments/assets/ff0c0e0c-82ad-4fdf-ac5c-cf66fc314b00)

![Added_Favourite](https://github.com/user-attachments/assets/a4bd2e2e-97e6-4a7a-8d91-ed7917abe62c)

![Removed_Favourite](https://github.com/user-attachments/assets/4efd966f-43dd-4225-ac99-77123707c84c)

![Favourites Screen](https://github.com/user-attachments/assets/edde4b15-f8da-4f67-81ec-5cae4e94b2ce)

![Unit_Change Screen](https://github.com/user-attachments/assets/892d8586-201e-4fb0-a309-5cca80ae0853)

![Farhenheit_Unit](https://github.com/user-attachments/assets/acfc1a21-1a32-4f50-a3e1-f25dfb701037)

![Celsius_Unit](https://github.com/user-attachments/assets/87bfa3a0-dc10-4bf5-a961-a629b5ce082c)

![About Screen](https://github.com/user-attachments/assets/f19c436b-be10-4785-b539-74f223364ebb)






