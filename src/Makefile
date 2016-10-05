all:
	make build
	make clean

build:
	make clean
	clear; clear
	javac Controller.java

run:
	java Controller input/property.csv input/plan.csv input/event.csv 2017 2020

clean:
	rm -f *.class