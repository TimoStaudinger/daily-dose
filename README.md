# DailyDose

Your daily dose of motivation and discipline, straight from Reddit's [/r/getmotivated][1] and [/r/getdisciplined][2]
right into your inbox.

[1]: https://www.reddit.com/r/getmotivated
[2]: https://www.reddit.com/r/getdisciplined

## Progress

* System Architecture: Done
* **Main Controller development: Ongoing**
* Reddit API integration: Done
* Subscriber database design: Done
* Subscriber database integration: Done
* *Mail rendering: Not started yet*
* **Mail API integration: Happening right now**
* *Subscriber Frontend developement: Not started yet*

## Architecture

DailyDose uses a Java backend to retrieve the day's top posts from /r/getmotivated and /r/getdisciplined over the
[Reddit API][3] using the [JRAW][4] Java API wrapper. After throwing together an HTML mail with this content,
it sends it to all subscribers. Easy as that, daily motivation right in your inbox!

[3]: https://www.reddit.com/dev/api
[4]: https://github.com/thatJavaNerd/JRAW

### Database

The backbone of the application is a MySQL database that holds all required information about the subscribers.

At the moment, it looks like this:

![Database ERM](https://timostaudinger.com/wp-content/uploads/2015/05/ERM.png)

The email address and name of each subscriber is stored in table `user`, in addition to an auto incrementing id and create/change timestamps. A user can be active or inactive and different email frequencies could be possible. In addition to that, each user has one to many tokens which will identify him during all changes he makes to his own account, stored in table `token`.

