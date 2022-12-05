package main

import (
	"log"
	"os"
	"sort"
	"strconv"
	"strings"
)

func main() {
	input, err := os.ReadFile("input")
	if err != nil {
		log.Fatal(err)
	}
	log.Printf("Got %d lines", len(input))
	elves := strings.Split(string(input), "\n\n")
	calories := make([]int, len(elves))
	for i, elf := range elves {
		for _, eline := range strings.Split(elf, "\n") {
			// log.Printf("Elf %d, eline=%s", i, eline)
			eline_cnt, _ := strconv.Atoi(eline)
			calories[i] = calories[i] + eline_cnt
		}
		log.Printf("Elf %d: %d calories", i, calories[i])
	}
	sort.Slice(calories, func(i, j int) bool {
		return calories[i] > calories[j]
	})
	log.Printf("largest: %d", calories[0])

	log.Printf("top three: %d", calories[0]+calories[1]+calories[2])
}
