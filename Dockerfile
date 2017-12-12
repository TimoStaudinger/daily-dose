FROM maven:3.5-alpine

USER root

RUN echo 'https://dl-3.alpinelinux.org/alpine/v3.5/main' > /etc/apk/repositories  && \
    echo '@testing https://dl-3.alpinelinux.org/alpine/edge/testing' >> /etc/apk/repositories && \
    echo '@community https://dl-3.alpinelinux.org/alpine/v3.5/community'

RUN apk update
RUN apk upgrade

RUN apk add python python-dev py-pip
RUN pip install awscli
