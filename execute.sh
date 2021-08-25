#!/bin/bash

for f in data/*; do
  java -jar target/VerbalCalculator-1.0-SNAPSHOT.jar --file="$f"
done

