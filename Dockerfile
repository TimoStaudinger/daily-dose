FROM maven:3-alpine

MAINTAINER Timo M. Staudigner <mail@timostaudinger.com>

RUN apt-get update && apt-get -y install python-pip python-yaml python-dev
RUN pip install awscli