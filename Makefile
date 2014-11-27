all:
	rm -f *.class
	javac *.java
	gcj -o a2 --main=A2 -Wall -Werror *.java
	astyle --indent=tab *.java > /dev/null 2> /dev/null
