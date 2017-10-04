# Fin

What Is This?
-------------

This application is intended to simulate a coffee-shop like environment with
two Baristas. The goal of the program is to determine the order to make the
series of coffeeshop drinks. This program has implemented two solutions for
this problem: The FIFO (First in, First out) solution that makes each drinks
in chronological order by the first available Barista, as well as a creative
solution (explained below) that takes an unconventional approach to solving
this problem.

Solution Details
----------------

1. The first method of the program, fifo, has been developed in accordance with
Fin's instructions. The method simply takes parses the input JSON into a JSON
Array and places it into a Queue for the sake of retrieving orders in the order
that they were received by the coffee shop.

2. The second method of the program, imadMethod, takes a slightly more complex
approach to this problem. The premise of this solution aims to effectively
utilize every minute of the day by minimizing the Baristas' free time while
simultaneously maximizing their utility. This problem is crucial in scenarios
where gaps can develop in the ordering times-- such as, in an oversimplified
example, with one Barista and the initial 3 orders being in the order of: one
placed at minute 1, another at minute 2, and another at minute 8. In order to
maximize the utility of this gap, the most practical solution is to ensure
that the drink made closest to this gap takes longer to make than the other.
For example, in this case, if the drink ordered at minute 1 takes 4 minutes to
make while the drink ordered at minute 2 takes 2 minutes to make, the simple
task of swapping the order of these two drinks can result in much less time
waited for the second orderer, and less time wasted during the 6 minute gap
for the barista. As long as the second order was not placed far afterwards,
it can very realistically be made before the first due to the difference in time
required to make them. In a real application of this, it would be more practical
to take the derivative of the changes of ordering times over some time and
make the drinks whenever the derivative spikes upward (meaning there comes
a sizable leap in ordering times). However, to simplify matters for this demo,
the orders are clustered together in blocks that represent a maximum of either
three minutes that pass over the course of the ordering times OR four individual
drink orders (if more than four are placed over a three minute interval). These
two factors, the time of each interval and maximum size of the blocks are
hyper-parameters for this problem since we don't know what is necessarily ideal
-- I landed at 3 and 4 respectively for the sake of practicality. Once these
blocks are created, the idea is to serve the drinks that take the least amount
of time to make first within each interval. This way, the free time that
results after each individual block will be left allocated for the most
time-consuming drinks to make. The drinks are now processed block by block, with
the shortest drinks being made first by the first available Barista. This way,
customers are more happy because it reduces the average weight time considerably
for those ordering easier-to-make drinks, and allows for more drinks to be
processed throughout the day with the same manpower.


How To Run the Example
----------------------

There are a few different ways to interact with the project

1. Since the project is written in Java, a Java IDE such as Eclipse
(recommended), Netbeans, or BlueJ may be utilized to directly deploy the
project on and test within the program. Note that the project is implemented
with a .jar file for the sake of utilizing Google's GSON, a JSON parsing
library, so this may be the easiest option. The jar file is included with
the rest of the program, and may be found under '/jars used/gson-2.8.0.jar'

2. Run it on Command Line/Terminal. The program can be
compiled directly on the command line; however it will require access to the
GSon jar file by Google for the API integration. For the sake of running this
project on the command line, the JSON parser jar file was found and provided
into the folder ‘/jars used/gson-2.8.0.jar’ By using this jar file is come
across, here are the steps to run this program on the command line:

Go to the path '/src/' and '/src/objects' to obtain all .java files.
Once these files are obtained, go into terminal/Command prompt, and compile these
files together with the added /jars used/gson-2.8.0.jar. Afterwards, simply run
the program from here on terminal and it should run the same as it does on
a traditional IDE.

3. Read the code. A great deal of effort has gone into making the example code
readable, not only in terms of the code itself, but also the extensive inline
comments and documentation blocks.
