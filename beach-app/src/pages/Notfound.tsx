import '../App.css';
import { Link } from "react-router-dom";


function Notfound() {
    return (
        <div >
            
            <h1>
              404 Beach not found
            </h1>
            <Link to='/Homepage' state={{returnHome: true}} >HOME</Link>
        </div>
      );
  };


export default Notfound;