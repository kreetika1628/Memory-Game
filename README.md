
# Memory Game

  

## Overview

This is a Java-based web memory game implemented using Servlets. The game involves a 4x4 grid of cards with pairs hidden. The player reveals cards to find matching pairs.

  

## Features

- 4x4 grid with shuffled pairs of cards.

- Tracks revealed cards dynamically.

- Simple and interactive UI.
  

## How to Run

1.  **Setup a Java IDE**

- Use IntelliJ IDEA or Eclipse with Jakarta Servlet dependencies.

2.  **Configure the Project**

- Install Maven or use `pom.xml` for dependencies.

- Add `jakarta.servlet` dependency.

3.  **Deploy**

- Use Apache Tomcat or a similar server.

- Place the `MemoryGame` directory under the server’s `webapps/` folder.

4.  **Run the Server**

- Start the Tomcat server.

- Visit `http://localhost:8080/MemoryGame` in your browser.

  

## Expected Output

- A webpage displaying a 4x4 grid with buttons representing hidden cards.

- On clicking a button, it reveals the card value.

- Matching pairs remain revealed.

- The game continues until all pairs are revealed.

  

## Dependencies

- Jakarta Servlet API

- Apache Tomcat (9 or higher)

  

## Notes

- Ensure Java 8 or higher is installed.

- Use the `styles.css` file in `static/` for custom styling.

  

## Contribution

Feel free to fork and contribute to the project by submitting pull requests.
