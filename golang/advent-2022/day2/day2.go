package main

import (
	"fmt"
	"log"
	"os"
	"strings"
)

func main() {
	input, err := os.ReadFile("input")
	if err != nil {
		log.Fatal(err)
	}
	total := 0
	rounds := strings.Split(string(input), "\n")
	// fmt.Printf("%d lines\n", len(rounds))
	for _, round := range rounds {
		moves := strings.Split(round, " ")
		total = total + score(moves[0], moves[1])
	}

	fmt.Printf("Total: %d\n", total)

	// part 2
	total2 := 0
	for _, round := range rounds {
		codes := strings.Split(round, " ")
		mymove := getmove(codes[0], codes[1])
		total2 = total2 + score(codes[0], mymove)
	}
	fmt.Printf("Total2: %d\n", total2)
}

func score(his, mine string) int {
	score := 3
	if mine == "X" {
		score = 1
	} else if mine == "Y" {
		score = 2
	}
	wonr := won(his, mine)
	log.Printf("score(%s,%s) = %d+%d", his, mine, score, wonr)

	result := score + wonr
	return result
}

func won(his, mine string) int {
	// A, X = Rock, B, Y = Paper, C, Z = scissors
	if (his == "A" && mine == "X") || (his == "B" && mine == "Y") || (his == "C" && mine == "Z") {
		return 3
	}
	if (his == "A" && mine == "Y") || (his == "B" && mine == "Z") || (his == "C" && mine == "X") {
		return 6
	}
	return 0
}

// figure out the value of my move given his move and outcome
func getmove(his, outcome string) string {
	loose := "X"
	win := "Z"
	rock := "A"
	paper := "B"
	if outcome == loose {
		if his == rock {
			return "Z" // scissors
		} else if his == paper {
			return "X" // rock
		}
		// his is scissors
		return "Y" // paper
	} else if outcome == win {
		if his == rock {
			return "Y" // paper
		} else if his == paper {
			return "Z" //scissors
		}
		// his is scissors
		return "X" // paper
	}
	// force a draw
	if his == rock {
		return "X"
	} else if his == paper {
		return "Y"
	}
	return "Z"
}
