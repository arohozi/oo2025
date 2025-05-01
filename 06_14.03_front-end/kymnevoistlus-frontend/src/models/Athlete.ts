import { AthleteInRace } from "./AthleteInRace"

export type Athlete = {
    id: number,
    name: string,
    firstName: string,
    lastName: string,
    age: number,
    country: string,
    athleteInRaces: AthleteInRace
  }