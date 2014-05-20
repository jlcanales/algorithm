Local Build and Deployment.
===========================

## Build ##
Check out source code from subversion. The svn command depends on the release you want to build
    
    For Version 6.3.XX maintenance
    >svn checkout http://svn2.intrayell.com/svn/412020_Correlador_Eventos/branches/cep-6.3.x
    
    For last snapshot Version 6.4.0-SNAPSHOT
    >svn checkout http://svn2.intrayell.com/svn/412020_Correlador_Eventos/trunk
	
Build the application using:
    
    >mvn clean install

## Deployment ##
### Eclipse Launching ###
After following the developer setup instructions, the application can be launched from eclipse IDE running as java
application the class *com.hibu.cepserver.standalone.ServerShellMain.java*
 
### Local deployment ###
1. Take the file *cepserver-standalone/target/cepserver-standalone-6.4.0.SNAPSHOT-distribution.tar.gz*
2. Unzip the file in a local directory, *$HOME/install* for example.
3. Customize queues and services with your local endpoints.

### Dev. Environment deployment ###
For Dev. Environment, it exists an automatic deployment script that install and start the distribution file.
To use this script a ssh connection must be available with dev machine.


1. Create a folder **/apps/yell01_correlador** in dev machine.
2. Setup the connectivity parameters in **cepserver-standalone/src/deploy/deploy.dev.properties**
3. Go to **deployer/utilies** directory.
4. Execute next command:


	ant -f deploy.xml -propertyfile ../../cepserver-standalone/src/deploy/deploy.dev.properties -verbose -Dlib=deploy_lib -lib deploy_lib echoproperties deploy-file 

Once the server is installed, cepserver can be stopped remotely executing next command:

	ant -f deploy.xml -propertyfile ../../cepserver-standalone/src/deploy/deploy.dev.properties -verbose -Dlib=deploy_lib -lib deploy_lib echoproperties cepserver-stop 

Or it can be started remotely executing next command:

	ant -f deploy.xml -propertyfile ../../cepserver-standalone/src/deploy/deploy.dev.properties -verbose -Dlib=deploy_lib -lib deploy_lib echoproperties cepserver-start 

For Development environment, the product is preconfigured. To see that configuration, please, look at
**cepserver-standalone/src/main/resources/properties**
	