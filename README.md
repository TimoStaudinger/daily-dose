# DailyDose

Your daily dose of motivation and discipline, straight from Reddit's [/r/getmotivated][1] and [/r/getdisciplined][2]
right into your inbox.

[1]: https://www.reddit.com/r/getmotivated
[2]: https://www.reddit.com/r/getdisciplined

## Architecture

DailyDose uses a Java backend to retrieve the day's top posts from /r/getmotivated and /r/getdisciplined over the
[Reddit API][3] using the [JRAW][4] Java API wrapper. After throwing together an HTML mail with this content,
it sends it to all subscribers. Easy as that, daily motivation right in your inbox!

[3]: https://www.reddit.com/dev/api
[4]: https://github.com/thatJavaNerd/JRAW
