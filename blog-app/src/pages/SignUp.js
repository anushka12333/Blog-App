import {useState} from 'react'
import { Card, CardBody, CardHeader, Container, FormGroup, Input, Label,Form, Button, Row  ,Col, FormFeedback} from "reactstrap";
import Base from "../components/Base";
import { toast } from 'react-toastify';
import {signUp} from "../services/user_service"
const SignUp =()=>{
    const [data, setData] = useState({
        name:'',
        email:'',
        password:'',
        about:'',
    })
    const [error, setError] = useState({
        errors:{},
        isError:false
    })
    //handle Change
    const handleChange =(event,property) =>{
        // console.log('name change');
        // console.log(event.target.value);
        setData({...data,[property]:event.target.value})
        
        
    }
    //reseting the form
    const resetData =()=>{
        setData({
            name:'',
        email:'',
        password:'',
        about:'', 
        })
    }
    //submit the form
    const submitForm=(event)=>{
        event.preventDefault();
        console.log(data);
       
        
        //data validate 
        //server 
        signUp(data).then((resp ) =>{
            console.log(resp);
            console.log("success log ");
            toast.success("User is registered successfully !! user id "+resp.id);           
         setData({
            name:'',
            email:'',
            password:'',
            about:'', 
         })
        }).catch((error) =>{
            console.log(error);
            console.log('error log');
            //handle error in proper way
            setError({
                errors:error,
                isError:true
            })           
            
        })
    }

    return (
    <Base>
    <Container>
       <Row className="mt-4">      
        <Col sm={{size:6,offset:3}}>
        <Card color="dark" inverse>
            <CardHeader>
              <h3> Fill Information To Register !!</h3> 
            </CardHeader>
            <CardBody >
                <Form onSubmit={submitForm}>
                    <FormGroup>
                        <Label for="name">Enter Name</Label>
                        <Input 
                            type="text"
                            placeholder="Enter here"
                            id="name"
                            onChange={(e) => handleChange(e,'name')}
                            value={data.name}
                            invalid={error.errors?.response?.data?.name ? true:false}
                        />
                        <FormFeedback >
                            {error.errors?.response?.data?.name }
                        </FormFeedback>
                      
                    </FormGroup>
                    <FormGroup>
                        <Label for="name">Enter Email</Label>
                        <Input 
                            type="email"
                            placeholder="Enter here"
                            id="email"
                            onChange={(e) => handleChange(e,'email')}
                            value={data.email}
                            invalid={error.errors?.response?.data?.email ? true:false}
                        />
                          <FormFeedback >
                            {error.errors?.response?.data?.email }
                        </FormFeedback>
                    </FormGroup>
                    <FormGroup>
                        <Label for="name">Enter Password</Label>
                        <Input 
                            type="password"
                            placeholder="Enter here"
                            id="password"
                            onChange={(e) => handleChange(e,'password')}
                            value={data.password}
                            invalid={error.errors?.response?.data?.password ? true:false}
                        />
                          <FormFeedback >
                            {error.errors?.response?.data?.password }
                        </FormFeedback>
                    </FormGroup>
                    <FormGroup>
                        
                        <Input 
                            type="textarea"
                            placeholder="Enter here"
                            id="about"
                            style={{height:"250px"}}
                            onChange={(e) => handleChange(e,'about')}
                            value={data.about}
                            invalid={error.errors?.response?.data?.about ? true:false}
                        />
                          <FormFeedback >
                            {error.errors?.response?.data?.about }
                        </FormFeedback>
                    </FormGroup>
                    <Container className="text-center">
                         <Button outline color="light">
                         Register
                         </Button> 
                        <Button onClick={resetData} outline type="reset" color="secondary" className="ms-2">Reset</Button>
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
export default SignUp;