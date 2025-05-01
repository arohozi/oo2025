import { useEffect, useRef, useState } from "react";
import { Athlete } from "../models/Athlete";
import { ToastContainer, toast } from 'react-toastify';
import { Link } from "react-router-dom";
 
function ManageAthletes() {
  const [athletes, setAthletes] = useState<Athlete[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/athletes")
        .then(res=>res.json())
        .then(json=> setAthletes(json.content))
  }, []);

  const deleteAthlete = (id: number) => {
    fetch(`http://localhost:8080/athletes/${id}`, {
      method: "DELETE",
    }).then(() => 
      setAthletes(athletes.filter(athlete => athlete.id !== id)));
    ;
  };

  const nameRef = useRef<HTMLInputElement>(null);
  const ageRef = useRef<HTMLInputElement>(null);
  const countryRef = useRef<HTMLInputElement>(null);

  const addAthlete = () => {
    let newAthlete = {
      name: nameRef.current?.value,
      age: Number(ageRef.current?.value),
      country: countryRef.current?.value,
      firstName: '',
      lastName: ''
    }

    const nameSplit = newAthlete?.name?.split(" ")
    if (nameSplit) {
      newAthlete.firstName = nameSplit[0]
      newAthlete.lastName = nameSplit[1]
    }

    fetch(`http://localhost:8080/athletes`, {
      method: "POST",
      body: JSON.stringify(newAthlete),
      headers: {
        "Content-Type": "application/json"
      }
    }).then(res=>res.json())
      .then(json=> {
        if (json.content !== undefined) {
          setAthletes(json.content);
          toast.success("Uus athlete lisatud!");
        } else {
          toast.error(json.message);
        }
      })
  }
 
  return (
    <div>
      <h2>Manage Athletes</h2>

      <label>Name</label> <br />
      <input ref={nameRef} type="text" /> <br />
      <label>Age</label> <br />
      <input ref={ageRef} type="number" /> <br />
      <label>Country</label> <br />
      <input ref={countryRef} type="text" /> <br />
      <br />
      <button onClick={() => addAthlete()}>Add athlete</button>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Country</th>
          </tr>
        </thead>
        <tbody>
          {athletes.map((athlete) => (
            <tr key={athlete.id}>
              <td>{athlete.id}</td>
              <td>{athlete.firstName} {athlete.lastName}</td>
              <td>{athlete.age}</td>
              <td>{athlete.country}</td>
              <td>
              <button onClick={() => deleteAthlete(athlete.id)}>Delete</button>
              </td>
              <td>
                <Link to={"/admin/edit-athlete/" + athlete.id}>
                  <button>Edit</button>
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <ToastContainer />
    </div>
  );
}
 
export default ManageAthletes;