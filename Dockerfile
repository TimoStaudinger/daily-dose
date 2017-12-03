FROM maven:3:alpine

MAINTAINER Timo M. Staudigner <mail@timostaudinger.com>

RUN apk add --update python python-dev py-pip
RUN pip install awscli