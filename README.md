# Bowling Score Calcultor
This programs calculates the bowling match score from a file.

## Installation
1. Just download or clone this source code. 
1. In your download folder (or local repository), type in your console **cd  bowling.shell**.
1. Then you will need to build this code with the command **mvn clean install**.
1. After that, you must type **java -jar target\bowling.shell-1.0-SNAPSHOT.jar** on Windows. On Linux-like OS, you should type **java -jar target/bowling.shell-1.0-SNAPSHOT.jar**.
1. Finally, you will see Spring Boot init screen.

## Usage
After the installation, you will see on your console spring-shell prompt (**shell:>**).
Here you can type:
* **shell:>bowl -op _origin_path_**.
The parameters are:
1. **origin path**: flags **-op** or **--origin-path**.
    - This parameter defines the source path of the file that contains the bowling match scores.
    - This parameter is **_Mandatory_**.
         
## Examples
* bowl -op /home/raven/SampleInput.txt