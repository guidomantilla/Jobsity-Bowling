# Bowling Score Calcultor
This programs calculates the bowling match score from a file.

## Installation
1. Just download or clone this source code. 
1. In your download folder (or local repository), type in your console **cd  bowling.shell**.
1. Then you will need to build this code with the command **mvn clean install**.

## Usage
After the installation, you must type 
1. On Windows: **java -jar target\bowling.shell-1.0-SNAPSHOT.jar [origin_path]** 
2. On Linux-like OS, **java -jar target/bowling.shell-1.0-SNAPSHOT.jar [origin_path]**

The parameters are:
1. **origin_path**:
    - This parameter defines the source path of the file that contains the bowling match scores.
    - This parameter is **_Mandatory_**.
    - This parameter can be either relative or absolute path.
         
## Examples
* java -jar target/bowling.shell-1.0-SNAPSHOT.jar /home/raven/SampleInput.txt