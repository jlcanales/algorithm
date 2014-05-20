CepEngine - Developer Setup
===================================

## Prerequisits ##

* Java JDK version 7
	
* Maven version 3.1.1
	
* Eclipse Kepler
	
## Getting the source ##

The source code is stored in a subversion repository hosted on a Subversion server.  You can use your favorite **svn** client to
get it.  
The URL to the repository is XXXXXXXXX where you
must provide your user and password to login.  You can check out the development mainline executing next
command.

All development should be done from the develop branch.

## Maven Configuration ##

Maven needs to be pointed at the correct artifact repositories so it can find the required dependencies.  I find the easiest 
way to do this is to add a new profile to your maven **settings.xml** file.  This file can be found under the **conf** 
directory of your maven install.  Add the following in the **\<profiles\>** section:
	
    <profile>
        <id>cepserver-profile</id>
        <activation>
                <activeByDefault>true</activeByDefault>
        </activation>
        <repositories>
            <repository>
                <id>ext-releases-local</id>
                <name>ext-releases-local</name>
                <url>http://yell-arquitectura.artifactoryonline.com/yell_arquitectura/ext-releases-local
                </url>
                <snapshots>
                        <enabled>false</enabled>
                </snapshots>
                <releases>
                        <enabled>true</enabled>
                        <updatePolicy>always</updatePolicy>
                </releases>
        	</repository>
        </repositories>
        <pluginRepositories>
            <pluginRepository>
                    <id>yell_arquitectura_plugin_releases</id>
                    <name>yell_arquitectura-releases</name>
                    <url>http://yell-arquitectura.artifactoryonline.com/yell_arquitectura/plugins-releases-local
                    </url>
            </pluginRepository>
        </pluginRepositories>
	</profile>	
	
	
	
If you have **multiple profiles**, then set the **\<activeByDefault\>** to *false*.  This means you will have to specify the 
profile in each build.
If this profile is not active by default, the above command to build the source changes to this:
	
	mvn clean install -P cmtfiledownload-profile


	
	
## Building the Code ##

In the root of the source run the following maven command:
	
	mvn clean install
	
This will build all of the modules, execute all of the unit tests and install the artifacts in your local maven repository.
Integration tests will be skipped.  (An integration test is any test class that has a name that ends in *Test*.)
	
## Running the Code ##

### Eclipse ###

You can run the application right through Eclipse. 

The first thing to do is set up a running configuration for class **com.hibu.cepserver.standalone.ServerShellMain**
 by going to Run | Run Configurations when serverShellMain.java file is selected.
 
Click the *Arguments* flag and add **-Dapp.env=dev** in the *VM Arguments* box.

Next, click on the *Classpath* flag, select **user entries** and push **Advanced**. In the next window you must select 
add external folder and select a folder where you have rights to read and write. That folder will store the file persistence
for EsperHA when it is activated.





