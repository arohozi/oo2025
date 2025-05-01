import { useEffect, useState } from 'react'
import { Athlete } from '../models/Athlete';

function Athlets() {
  const [athlete, setAthlete] = useState<Athlete[]>([]);
  
  useEffect(() => {
    fetch("http://localhost:8080/athletes")
        .then(res=>res.json())
        .then(json=> setAthlete(json.content))
  }, []);

  return (
    <div>
      <div>Athletes:</div>

      {athlete.length > 0 && (
        <div>
          {athlete.map(a => `${a.firstName} ${a.lastName}`).join(", ")}
        </div>
      )}
    </div>
  );

}
export default Athlets