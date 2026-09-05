# Jaikin

## Project

Implement **Chaikin's algorithm** and display its iterations step by step on a canvas.

The application must:
- Allow the user to place control points with the left mouse button.
- Display the points on the canvas.
- Start the animation with `Enter`.
- Run through 7 iterations, then restart from the beginning.
- Do nothing when `Enter` is pressed with no points.
- Display a single point when there is only one.
- Draw a straight line when there are exactly two points.
- Close the window with `Escape`.

## Team structure

### `Screen.java` — Person 1

Responsible for the **window, canvas, input and drawing**.

Tasks:
- Create and manage the window and canvas.
- Handle left mouse clicks.
- Handle `Enter` and `Escape`.
- Draw points and lines.
- Refresh the canvas during the animation.
- Call `Manager` when an action is required.

`Screen` should not contain the Chaikin algorithm.

### `Manager.java` — Person 2

Responsible for the **application state and animation logic**.

Tasks:
- Store the control points.
- Store the current points used for the current iteration.
- Keep track of the current step (`0` to `7`).
- Start/restart the animation.
- Request the next iteration from `Algo`.
- Handle the special cases:
  - 0 points
  - 1 point
  - 2 points

`Manager` should not contain drawing code or the mathematical implementation of Chaikin.

### `Algo.java` — Person 3

Responsible for **Chaikin's algorithm only**.

Tasks:
- Implement one iteration of Chaikin's algorithm.
- Take a list of points as input.
- Return the new list of points.
- Do not modify the original list.

For each pair of consecutive points `P1` and `P2`:

```text
Q = 3/4 P1 + 1/4 P2
R = 1/4 P1 + 3/4 P2
```

The algorithm should work independently from the window and user input.

## Shared `Point` class

If needed, create a separate `Point.java` class containing the coordinates of a point.

Keep this class simple. It should only represent a point and provide access to its coordinates.

## Important

Before coding, agree on the methods and types exchanged between the three classes.

Main flow:

```text
Mouse click
    ↓
Screen
    ↓
Manager
    ↓
Algo
    ↓
Manager
    ↓
Screen
    ↓
Canvas
```

Each class should stay focused on its own responsibility.


 audit
Restart the program, set two control points by left-clicking on the canvas, and press Enter.
Is only a straight line drawn between the two control points?
Restart

Does the animation complete 7 steps before restarting?
