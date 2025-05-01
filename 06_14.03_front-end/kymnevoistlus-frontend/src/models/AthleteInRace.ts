import { Athlete } from "./Athlete"
import { Race } from "./Race"

export type AthleteInRace = {
    id: number,
    athlete: Athlete,
    race: Race
  }