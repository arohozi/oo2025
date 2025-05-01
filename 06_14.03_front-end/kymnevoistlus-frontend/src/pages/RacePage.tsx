import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"
import { Race } from "../models/Race";
import { Athlete } from "../models/Athlete";

function RacePage() {
  const {raceId} = useParams();
  const [race, setRace] = useState<Race>();
  const [athletes, setAthletes] = useState<Athlete[]>([])

  useEffect(() => {
    fetch("http://localhost:8080/race/" + raceId)
      .then(res => res.json())
      .then(json => setRace(json));
  }, [raceId]);

  useEffect(() => {
    fetch(`http://localhost:8080/athletes-races?raceId=${raceId}`)
      .then(res => res.json())
      .then(json => setAthletes(json.content));
  }, [raceId]);

  return (
    <div>
      <div>Nimi: {race?.name}</div>

      {athletes.length > 0 && (
        <div>
          Osalejad: {athletes.map(a => `${a.firstName} ${a.lastName}`).join(", ")}
        </div>
      )}
    </div>
  );
  
}

export default RacePage