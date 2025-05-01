import { Athlete } from "./Athlete"
import { Race } from "./Race"

export type Result = {
    id: number,
    date: Date,
    checkpoint: string,
    athlete_id: Athlete,
    race_id: Race
  }