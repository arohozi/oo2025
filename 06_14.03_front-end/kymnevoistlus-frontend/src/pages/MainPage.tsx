import { useCallback, useEffect, useRef, useState } from 'react'
import { Athlete } from '../models/Athlete';
import { Race } from '../models/Race';
import { Link } from 'react-router-dom';

function MainPage() {
  const [athlete, setAthlete] = useState<Athlete[]>([]);
  const [race, setRace] = useState<Race[]>([]);
  const [totalRace, setTotalRace] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [raceByPage, setRaceByPage] = useState(1);
  const [page, setPage] = useState(0);
  const [activeCategory, setActiveCategory] = useState(-1);
  const [sort, setSort] = useState("id,asc");
  const raceByPageRef = useRef<HTMLSelectElement>(null);
  
  useEffect(() => {
    fetch("http://localhost:8080/athletes")
        .then(res=>res.json())
        .then(json=> setAthlete(json.content))
  }, []);

  const showByCategory = useCallback((categoryId: number, currentPage: number) => {
    setActiveCategory(categoryId);
    setPage(currentPage);
    fetch("http://localhost:8080/races" + 
      "?size=" + raceByPage +
      "&page=" + currentPage +
      "&sort=" + sort
    )
        .then(res=>res.json()) 
        .then(json=> {
          setRace(json.content);
          setTotalRace(json.totalElements);
          setTotalPages(json.totalPages);
        })
  }, [raceByPage, sort])

  useEffect(() => {
    showByCategory(activeCategory, 0);
  }, [showByCategory, activeCategory]);

  function updatePage(newPage: number) {
    showByCategory(activeCategory, newPage);
  }

return (
    <div>
      <button onClick={() => setSort("id,asc")}>Sorteeri vanemad enne</button>
      <button onClick={() => setSort("id,desc")}>Sorteeri uuemad enne</button>
      <button onClick={() => setSort("name,asc")}>Sorteeri A-Z</button>
      <button onClick={() => setSort("name,desc")}>Sorteeri Z-A</button>

      <select ref={raceByPageRef} 
              onChange={() => setRaceByPage(Number(raceByPageRef.current?.value))}>
        <option>1</option>
        <option>2</option>
        <option>3</option>
      </select>
      <br />
      <br />
      <div>Kokku race'id: {totalRace} tk</div>
      {race.map(race => 
      <div key={race.id}>
        <div>{race.id}</div>
        <div>{race.name}</div>
        <Link to={"/race/" + race.id}>
          <button>Vt lähemalt</button>
        </Link>
      </div> )}
      <button disabled={page === 0} onClick={() => updatePage(page - 1)}>Eelmine</button>
      <span>{page + 1}</span>
      <button disabled={page >= totalPages - 1} 
        onClick={() => updatePage(page + 1)}>Järgmine</button>    
    </div>
  )
}

export default MainPage