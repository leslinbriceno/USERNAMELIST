# USERNAMELIST
Solution to the Intertec Intl test problem

# Installation
1.- Create a folder wherever you want in the test machine.
2.- Create an environment variable named "INTERTECINTL_HOME" pointing to the folder created in the step 1.
3.- Unzip the content of the file CONFIG.zip stored in the repository folder named "resources" into the folder created in the step 1.
4.- Copy the JAR file "UserNameList.jar" stored in the repository folder named "dist" into the folder created in the step 1.

If you want to execute the application, please follow the steps described in the Mode of use section.

# Resources
Inside the directory <INTERTECINTL_HOME>/USERNAMELIST/config, you will find five configuration files:
1.- USERNAMELIST.dtd: This file define the structure and syntax of the USERNAMELIST.xml file.
2.- USERNAMELIST.xml: This file contains the general configuration of the application (restricted usernames file, used usernames file, etc).
3.- log4j.properties: This file define Log4j configuration used by the application.
4.- usedNames.list: This file contains the used usernames.
5.- restrictedNames.list: This file aontains the restricted usernames.

If you want to use a different used usernames file or a different restricted usernames file, you have to configure the name into USERNAMELIST.xml and create the respective file within the folder <INTERTECINTL_HOME>/USERNAMELIST/config

However if you want to add a new restricted username or a new used username, only edit the respective file and add the new username.

Important: The structure both used usernames file and restricted usernames file shuold be one username per line.


# Mode of use
1.- Open a command prompt console
2.- Change the directory to the folder where is pointing the environment variable named "INTERTECINTL_HOME"
3.- Execute: java -jar UserNameList.jar <username_to_check>
		Where <username_to_check> is the username you want to check