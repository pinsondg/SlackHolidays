# Slack Holiday Finder

This program finds the company holidays and notifies employees if there is a holiday coming up via slack. This program was
written in Java using the simple-slack-api downloaded from https://github.com/Ullink/simple-slack-api.git. In order to work properly,
the program has to be run on either a computer or a server. If the program is terminated, the bot will not respond in slack.
The bot is currently running on a host computer in the Charlottsville Radiant Solutions office and will reply in their slack
channel. It will eventually have to be uploaded to the cloud or another server and the creators are looking into that. We 
are also looking into different customization options for the bot so it is easy for the user to customize their experience.

## Getting Started

To get the project running, you will have to run it as a Maven project on either your server or personal computer.
Because it is written in Java, it will run on any computer with the Java Virtual Machine installed. 

### Prerequisites

You will need the Java Virtual Machine installed on your computer in order to run this program. To install Java,
you can visit their website at https://java.com/download.

If using a linux system, you can install Java using the command:

```
$ sudo apt-get install default-jre
```

First, you'll need to install Maven to run the application from the command line.
If using Ubuntu, use:

```
$sudo apt-get install mvn
# if that does not work, try
$sudo apt-get install maven
```
For manual installation on other operating systems, the installation page can be found at this link:  
http://maven.apache.org/download.cgi  
For a detailed description of how to download, visit the following page:  
http://maven.apache.org/download.html#Installation

Also, to run the application you can just run the SlackHolidays.jar file found in the repo.

To get this application to connect to an app in slack, you will first have to create a bot in the [Slack API Website](https://api.slack.com). 

To create an app, click "Start Building" and then "Create a New App" (you may have to sign in to your slack account first").
Give your new app a name and select what workspace you want it to be developed in. Now you will be on
the main page where you can customize your bot.

To get the app you just created to work with the java code, go navigate to add a bot user and give it any name you like.
Once you have added a bot user you can install it to your workspace. To do this, just hit install app and follow the instructions.
Once the app is installed you will be given a Bot User OAuth Access Token. You will need to copy this token and paste
it into the java file "MessageSender.java" where it specifies with a comment.

After you enter the token you can invite the bot to any channel you like and run it.

### Installing

To install, just press the green download button.

## Deployment

To deploy this program on a server or computer, you need to run it right at startup. There are many different ways to do this.  

We used ubuntu to deploy our app. To deploy right at startup on ubuntu, follow this think: https://askubuntu.com/questions/99232/how-to-make-a-jar-file-run-on-startup-and-when-you-log-out

## Using the Bot

As of now, the only features of this bot are that it will reply with the next holiday whenever the
words "next holiday" appear in a message. It will also let every channel it is assigned to if there is a holiday coming
up in the week on Mondays at 9am. More features could be added in future releases. 

## Built With

* [Java](https://java.com) - Software Development
* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/downloads) - IDE


## Authors

Daniel Pinson dpinson@radiantsolutions.com  Vamsi Yadav yyadav@radiantsolutions.com

