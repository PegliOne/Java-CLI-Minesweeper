# Java-CLI-Minesweeper

A minesweeper game made in Java that can be run in the command line.

## Features

- Board width, height and bomb count selection via user inputs
- Evenly sized squares (including on the left and right edges)
- Error handling for invalid inputs (including non-numerical inputs and inputs that are too low or two high)
- Cascades of square reveals for squares with zero bombs surrounding them
- Reveals all the bomb squares after a game over

## How To Use

### Set Up

To run the app, open the folder containing it as a project in Eclipse, then press the "run" button.

The app will prompt you to enter a board width (between 1 - 28), then a board height (between 1 - 28), then a bomb count (between 1 and the total number of squares on the board minus 1). Spaces added to numerical inputs are ignored.

If an invalid value is entered for the width, height or bomb count, the app will continue to prompt the user until a valid value is provided.

### Gameplay

To select a coordinate enter an x value, followed by a space and then a y value (i.e. x y). The origin is the top left of the board, so increasing the x value of your selection selects a square futher to the right, while increasing its y value selects a square futher down.

If a selected square doesn't have a bomb, the app with print "No bomb there". The board will then reprint with the selected square's value showing. The value corresponds to the number of bombs surrounding the square. If it is zero, the surrounding squares will be revealed and a cascasde will be triggered (i.e. other squares revealed to have a value of zero will have their surrounding squares revealed and so on until the cascade ends).

If the selected square does have a bomb, the game will end and the app will print "Boom! Game Over" followed by a reprint of the board with all the bomb squares revealed.

When all the safe squares have been revealed, the game will end and the app will print "All safe squares revealed. You win!" followed by a reprint of the board with all the bomb squares revealed.

## Upcoming Improvements

- Preventing the board from reprinting after an invalid square selection
- Enabling the width, height and bomb count to be set using a configuration.json file

## Technologies Used

- Java (in an Eclipse environment)
