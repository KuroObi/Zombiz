Git(install):

1.)Download and install latest Git Version:

   -> http://git-scm.com/downloads

2.)Open the "Git Bash"

3.)Set your Username and E-Mail:

	$ git config --global user.name "Your Name Here"

	$ git config --global user.email "your_email@example.com"

4.)Navigate(with cd "Folder") to your Project Folder

5.)Register on GitHub.com

6.)Joint the Project (https://github.com/KuroObi/Zombiz)

7.)Clone the Repository: git clone https://github.com/KuroObi/Zombiz.git

8.)Change to the Projekt Folder: cd Zombiz

9.)set the Repository: git remote add stream https://github.com/KuroObi/Zombiz.git

10.)Failsafe if something is missing: git fetch stream



Maven(install):

1.) Download and install Maven(http://maven.apache.org/download.cgi)

2.) open the cmd/shell

3.) change directory to the Project Folder (cd ..)

4.) deploy the Project with dependecys

4.1)Eclipse: $ mvn eclipse:clean OR use the Plugin

4.2)NetBeans: use the pom.xml ootb



Git(Commands):

Update your local Project

1.)$ git pull

Create your own branche(dont push into the Master!):

1.)$ git checkout -b [YourBrancheName]

Commit Something to your branche:

1.)$ git add [Data]

2.)$ git commit -m 'Your Text Here!'

3.)$ git remote add origin https://github.com/KuroObi/Zombiz.git

4.)$ git push origing [YourBrancheName]