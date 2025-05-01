import './App.css'
import { Route, Routes } from 'react-router-dom'
import Athlets from './pages/Athlets';
import ManageAthlets from './pages/ManageAthlets';
import Menu from './components/Menu';
import MainPage from './pages/MainPage';
import RacePage from './pages/RacePage';
import EditAthlete from './pages/EditAthlete';


function App() {

  return (
    <>
   
      <Menu />

      <Routes>
        <Route path="/" element={ <MainPage /> } />
        <Route path="/admin/athletes" element={ <ManageAthlets /> } />
        <Route path="/admin/edit-athlete/:athleteId" element={ <EditAthlete /> } />

        <Route path="/athletes" element={ <Athlets /> } /> 
        <Route path="/race/:raceId" element={ <RacePage /> } />

        <Route path="/*" element={ <div>Page not found</div> } />
      </Routes>

    </>
  )
}


export default App