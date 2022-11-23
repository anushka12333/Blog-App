import Base from "../components/Base";
import {useState} from 'react'
import { Card, CardBody, CardHeader, Container, FormGroup, Input, Label,Form, Button, Row  ,Col} from "reactstrap";
import { toast } from "react-toastify";
import { loginUser } from "../services/user_service";
import {doLogin} from '../auth'
import { useNavigate } from "react-router-dom";
const Login =()=>{
    const navigate =useNavigate();
    const [loginDetail, setLoginDetail] = useState({
        username:'',
        password:''
    })
    const handleChange=(event,field)=>{
       let actualValue= event.target.value
       setLoginDetail({
        ...loginDetail,
        [field]:actualValue
       })
    }
    const handleReset=()=>{
        setLoginDetail({
            username:'',
            password:''
        })
    }
    const handleFormSubmit=(event)=>{
        event.preventDefault();
        console.log(loginDetail);
        //validation
        if(loginDetail.username.trim()==='' || loginDetail.password.trim()===''){
            toast.error("Username or Password is required !! ")
            return
        }
        //submit the data to server to generate token
        loginUser(loginDetail).then((data)=>{           
            console.log(data)  
            //save the data to localstorge
            doLogin(data,() =>{
                console.log('login detail saved to localstorage');
                //redirect to user dashboard   
                navigate("/user/dashboard")             
            }) 
            toast.success("LogIn Success !!")
        }).catch(error => {
            console.log(error);
            if(error.response.status===400 || error.response.status===404){
                toast.error(error.response.data.message)
            }
            else{
                toast.error("Something went wrong on server !!")
            }
            
            
        })
    }
    return (
        <Base>
        <Container>
           <Row className="mt-4">
            <Col sm={{size:6,offset:3}}>
            <Card color="dark" inverse>
                <CardHeader>
                  <h3>Login Here !!</h3> 
                </CardHeader>
                <CardBody >
                    <Form onSubmit={handleFormSubmit}>                    
                    <FormGroup>
                        <Label for="name">Enter Email</Label>
                        <Input 
                            type="email"
                            placeholder="Enter here"
                            id="email"
                            value={loginDetail.username}
                            onChange={(e)=>handleChange(e,'username')}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label for="name">Enter Password</Label>
                        <Input 
                            type="password"
                            placeholder="Enter here"
                            id="password"
                            value={loginDetail.password}
                            onChange={(e)=>handleChange(e,'password')}
                        />
                    </FormGroup>                    
                    <Container className="text-center">
                            <Button outline color="light">
                            Login
                            </Button> 
                        <Button onClick={handleReset} outline type="reset" color="secondary" className="ms-2">Reset</Button>
                    </Container>
                    </Form>
                </CardBody>
            </Card>
            </Col>
           </Row>
        </Container>
    
       
    
            
        </Base>
    );
}
export default Login;