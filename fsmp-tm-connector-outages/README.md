##Overview
This is the implementation of the FSMP SO (Power Services) Outages Connector that the Outages aggregators call
into. This is the implementation that will return Power Services specific data.

It is a spring boot project that is deployable to the Predix Cloud Platform. 

## Getting Started
You need to make sure you have the credentials setup for Artifactory in your Maven settings.xml file. Artifacory makes
this more complicated then it needs to be, but the steps below should get you there:

1. In your browser go to https://devcloud.swcoe.ge.com/artifactory and login.
2. Click `Maven Settings` on the left hand side of the screen.
3. Click `Generate Settings`.
4. Click `Download Settings`.
5. Open the file downloaded in your favorite text editor (unless it is VIM, then use something else)
6. Hightlight one of the server tag goups toward the top of the file and copy it to your clipboard.
   The tag group should look something like:  
   
   ```xml
   <server>
     <username>212391951</username>  
     <password>kdsjfghkdjfhjkdshfkjshdf</password>  
     <id>central</id>  
   </server>  
   ```
6. Open the `~/.m2/settings.xml` in your favorite text editor.
7. If you do not have a tag group toward the top called `<servers>` create an opening and closing tag.
8. After the opening `server` tag paste the contents from your clipboard.
9. Change the `id` to be `swcoealppcr01` and save the file.

You should now be build the project.

Import this project as maven project in STS IDE (spring tool suite). You need 
to build the project using maven build.

```
$ mvn clean package
```

# Deploy to Predix Cloud

Please follow to these steps after getting predix cloud account to install 
Cloud foundry CLI: https://predix-io.grc-apps.svc.ice.ge.com/docs#IqAL3Fzb

1. Follow the instruction at https://github.build.ge.com/nola-build-guild/cf-push-zero to get the blue-green deployment plugin installed.
2. Log in to the appropriate space.
2. Run `./push.sh`.

Your application should be deployed and started in predix cloud. Based on 
route url, you should access the application here. URL
reference-app.grc-apps.svc.ice.ge.com

## Project structure

The source is structured according the FSMP Coding Standards.
