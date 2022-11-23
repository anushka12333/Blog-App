import { UNSAFE_RouteContext } from 'react-router-dom';
import Base from '../components/Base'
import userContext from '../context/userContext';
const About=()=>{
    return (
       <userContext.Consumer>
        {
            (user)=>(
                <Base>
                <h1>this is about : {user.name}</h1>
                </Base>
             
            )
        }
       </userContext.Consumer>
    )
}
export default About;