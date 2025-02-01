#!/bin/bash

# Wait for MySQL to be available
until mysql -h mysql -u root -prootpassword -e 'select 1'; do
  echo "Waiting for MySQL to be available..."
  sleep 2
done

# Start the Player Service
java -jar /player-service.jar
