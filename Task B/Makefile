
all: pack

run: 
	java -jar Program.jar

pack:
	javac *.java -d .
	jar cfe Program.jar Main *.class
	
clean:
	rm -f *.class
	rm -f Program.jar
