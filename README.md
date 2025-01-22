
# 🎮 **BaBall: Exploding Fun in Your Pocket!**

**BaBall** is the ultimate mini-game for mobile, where the goal is simple:  
**Make balls collide. Watch them explode. Beat the timer.**

This Kotlin-powered Android game is built with Jetpack Compose, delivering silky-smooth animations, explosive visuals, and a fast-paced challenge that'll keep you hooked. It's easy to play, hard to master, and even harder to put down.

----------

## ✨ **What Makes BaBall Awesome?**

🔥 **Bouncing Balls Madness:** Click anywhere on the screen to create bouncing balls.  
💥 **Explosive Action:** Watch balls collide and burst into stunning explosion animations.  
⏱️ **Race the Timer:** You’ve only got 20 seconds to rack up the highest score.  
🎨 **Custom Gaming Vibes:** Sleek UI, bold colors, and a retro gaming font.  
⚡ **Smooth as Butter:** Optimized for performance with real-time collision detection.  
📱 **Full Immersion:** Runs in fullscreen with no distractions—just pure gameplay.

----------

🎥 DEMO
👉 Watch BaBall in Action! 👇

https://github.com/user-attachments/assets/83b6f674-2ed7-4549-a483-0abf547268d3



---------

## 🚀 **How to Play**

1.  **Start the Game:**  
    Tap the screen when prompted. The first ball spawns, and the timer starts ticking!
    
2.  **Create More Balls:**  
    Tap anywhere to create additional bouncing balls.
    
3.  **Cause Explosions:**  
    Balls collide, build up a collision count, and eventually explode when the threshold is reached.
    
4.  **Beat the Timer:**  
    The game ends when the timer hits zero. Your score? The number of balls you exploded!
    
5.  **Restart the Fun:**  
    After the game ends, hit **Restart** to try again and beat your high score.
    

----------

## 🛠️ **How It Works Under the Hood**

### **Core Features:**

-   **Dynamic Ball Physics:**  
    Balls bounce, collide, and move with realistic physics, accounting for gravity, damping, and screen boundaries.
    
-   **Explosive Animations:**  
    Each collision builds a counter. When the threshold is reached, explosions with animated radii and opacity take over.
    
-   **Efficient Collision Detection:**  
    A spatial grid ensures only nearby balls are checked for collisions, keeping the game fast and responsive.
    
-   **Timer Logic:**  
    A countdown runs in the background, creating urgency and upping the challenge.
    

----------

## 🧱 **Code Architecture**

### **1. Domain Layer**

-   `Ball`: Represents a bouncing ball (position, velocity, collision count, etc.).
-   `Explosion`: Handles explosion animations for balls.
-   `SimulateBallsUseCase`: Updates ball physics, checks collisions, and processes explosions in real-time.

### **2. Presentation Layer**

-   `OptimizedExplodingBallsScreen`: The main game screen.
-   `StartScreen`: Splash screen prompting players to start the game.
-   `EndScreen`: Displays the final score with a restart option.
-   `GameHeader`: Shows the timer and explosion counter.
-   `BallCanvas`: Handles rendering of balls and explosions.

### **3. Utility Layer**

-   `processCollisions`: Handles ball-to-ball collisions.
-   `processExplosions`: Triggers and manages explosion animations.
-   `updateBallPositionAndVelocity`: Updates ball positions based on physics.
-   `addBall`: Adds a new ball to the game.

### **4. Theming**

-   **Custom Font:** Includes a retro gaming font for titles and headers.
-   **Colors:** Gradient background from vibrant blue to white for immersive gameplay.

----------

## 💻 **Setup Instructions**

### **Prerequisites:**

-   **Android Studio Flamingo** (or later)
-   **Kotlin 1.9+**
-   **Minimum SDK:** Android 5.0 (API 21)

### **Installation:**

1.  Clone the repo:
        
    `git clone https://github.com/username/baball.git` 
    
2.  Open in Android Studio and sync Gradle.
3.  Run the app on your favorite emulator or physical device.

----------

## 🎯 **Keywords**

-   Kotlin Game Development
-   Jetpack Compose Game
-   Android Real-Time Animation
-   Physics-Based Games
-   Explosive Effects in Android
-   Fullscreen Android Games
-   Fun Timer-Based Games
-   Retro Gaming UI

----------

## ❤️ **Contributing**

Love the game? Want to make it better? Contributions are welcome! Feel free to:

-   Submit issues for bugs or feature requests.
-   Create pull requests for improvements or new features.

----------

## 📜 **License**

This project is licensed under the **MIT License**—hack away and have fun!

----------

## 🌟 **Join the Fun!**

🚀 Get started today and create a chain reaction of fun!  
💬 Feedback? Suggestions? Let us know!
