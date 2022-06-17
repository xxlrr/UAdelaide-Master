#!/bin/bash

nodes="$1"


for i in `seq 1 ${nodes}`
do
	echo "N${i}"
done

echo "LINKSTATE"

for i in `seq 1 ${nodes}`
do
	for j in `seq $((i+1)) ${nodes}`
	do
		w=$((RANDOM%50+1))
		echo "N${i} N${j} ${w}"
	done
done

echo "END"
