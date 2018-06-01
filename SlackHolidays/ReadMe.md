# Slack Holiday finder

This program finds the company holidays and notifies employees if there is a holiday coming up via slack. This program was
written in Java using the simple-slack-api downloaded from https://github.com/Ullink/simple-slack-api.git. In order to work properly,
the program has to be run on either a computer or a server. If the program is terminated, the bot will not respond in slack.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

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

### Installing

To install, just press the green download button.

## Deployment

To deploy this program on a server or computer, you need to run it right at startup. There are many different ways to do this.  

We used ubuntu to deploy our app. To deploy right at startup on ubuntu, follow this think: https://askubuntu.com/questions/99232/how-to-make-a-jar-file-run-on-startup-and-when-you-log-out

## Built With

* Java (https://java.com) - Software Development
* [Maven](https://maven.apache.org/) - Dependency Management
* Eclipse (https://www.eclipse.org/downloads) - iDE


## Authors

Daniel Pinson dpinson@radiantsolutions.com, Vamsi Yadav yyadav@radiantsolutions.com

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

