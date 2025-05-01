import { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Athlete } from "../models/Athlete";
import { ToastContainer, toast } from 'react-toastify';

function EditAthlete() {
  const {athleteId} = useParams();
  const nameRef = useRef<HTMLInputElement>(null);
  const ageRef = useRef<HTMLInputElement>(null);
  const countryRef = useRef<HTMLInputElement>(null);
  const [athlete, setAthlete] = useState<Athlete>();
  const navigate = useNavigate();

  useEffect(() => {
    fetch("http://localhost:8080/athletes/" + athleteId)
      .then(res => res.json())
      .then(json => setAthlete(json));
  }, [athleteId]);

  const editAthlete = () => {
    const splitName = nameRef.current?.value.split(" ")
    const modifiedProduct = {
      id: athleteId, 
      firstName: splitName ? splitName[0] : "",
      lastName: splitName ? splitName[1] : "",
      age: Number(ageRef.current?.value),
      country: countryRef.current?.value
    }

    fetch("http://localhost:8080/athletes", {
      method: "PUT",
      body: JSON.stringify(modifiedProduct),
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then(json => {
        if (json.message && json.timestamp && json.status) {
          toast.error(json.message);
        } else {
          navigate("/admin/athletes");
        }
      }); 
  }

  if (athlete === undefined) {
    return <div>Athlete not found</div>
  }

  return (
    <div>
      <label>Name</label> <br />
      <input ref={nameRef} defaultValue={`${athlete.firstName} ${athlete.lastName}`} type="text"/>
      <label>Age</label> <br />
      <input ref={ageRef} defaultValue={athlete?.age} type="number" /> <br />
      <label>Country</label> <br />
      <input ref={countryRef} defaultValue={athlete?.country} type="text" /> <br />
      <button onClick={editAthlete}>Edit athlete</button>
      <ToastContainer />
    </div>
  )
}

export default EditAthlete