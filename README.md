# Robot-code
Code for the Remote-controlled robot made for our Computer Science teacher who was isolating.

The code works by having a stand-in computer between the Controller and the Robot. This stand-in computer allows for external connections to the network, and redirects the input into the robot.

The Controller runs the PythonInputClient, while the stand-in computer runs PythonServerClient, which connects to the robot which is running robotNetworkController.

The PythonServerClient writes to a txt file RobotStatus, of which is read by the robot to fetch what action it should currently be performing. I wasn't entirely proud of this conclusion, however at the time of programming it I wasn't all that familiar with Java networking, so file reading was the easiest thing for me to do.

Admittedly, its a bit of a bodge job, but I was only going after something that works with a span of 5 days of development, and no money to buy new hardware to assist :p
