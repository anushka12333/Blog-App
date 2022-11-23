import{ React ,useEffect,useState,useRef} from 'react'
import {Card,CardBody,Form,Label,Input, Container, Button} from 'reactstrap'
import { toast } from "react-toastify";
import {loadAllCategories} from '../services/catogory_services'
import JoditEditor from 'jodit-react'
import {createPost as doPost, uploadPostImage}  from '../services/post_services'
import { getCurrentUSerdetail } from '../auth'
const AddPost=()=>{
    const [categories, setCategories] = useState([])
    const [user, setUser] = useState(undefined)
    const editor = useRef(null)
   // const [content, setContent] = useState('')
    const[post,setPost]= useState({
        title:'',
        content:'',
        categoryId:''
    })
    const [image, setImage] = useState(null)
    useEffect(
        ()=> {
          setUser(getCurrentUSerdetail())
            loadAllCategories().then((data) =>{
          //   console.log(data);   
             setCategories(data)         
            }).catch(error =>{
             console.log(error);
             
            })
         },[]
    )
    const fieldChange=(event)=>{
     setPost({...post,[event.target.name]:event.target.value})     

    }
    const contentFieldData = (data)=>{
        setPost({...post,'content':data})

    }
    const createPost=(event)=>{
        event.preventDefault();
      //  console.log(post);
        if(post.title.trim() ===''){
            alert("post title is required !!")
            return;
        }
        if(post.content.trim() ===''){
            alert("post content is required !!")
            return;
        }
        if(post.categoryId.trim() ===''){
            alert("select some category  !!")
            return;
        }
        
        //submit the form one server
        
        post['userId']=user.id;
        doPost(post).then(data =>{

            uploadPostImage(image,data.postId).then(data=>{
                toast.success("Image Uploaded !!")
            }).catch(error=>{
                toast.error("Error in uploading image")
                console.log(error);
                
            })

            toast.success("Post Created !!")
            // console.log(post);
            setPost({
                title:'',
                content:'',
                categoryId:'',
                category:''
            })
            
        }).catch((error)=>{
            toast.error("Post not Created due to some error");            
            console.log(error);
            
        })

    }
    //handling file change event
    const handleFileChange=(event)=>{
        console.log(event.target.files[0]);
        setImage(event.target.files[0]);
        
    }
   return (
    <div>
    <div className="wrapper  mt-4">

        <Card className='shadow-ms border-0'>
            <CardBody>
            <h3>What going in your mind ?</h3>
                    <Form onSubmit={createPost}>
                        <div className="my-3">            
                        <Label for="title">Post Title </Label>
                        <Input 
                        type="text" 
                            id="title"
                            placeholder='Enter here'
                            className='rounded-0'
                            onChange={fieldChange}   
                            value={post.title}                         
                            name="title"
                        />                      
                        </div>
                        <div className="my-3">            
                        <Label for="content">Post Content </Label>
                        {/* <Input 
                        type="textarea" 
                            id="content"
                            placeholder='Enter here'
                            className='rounded-0'
                            style={{height:'300px'}}
                        /> */}
                      <JoditEditor 
                        ref={editor}
                        value={post.content}
                        onChange={contentFieldData}
                      />
                        </div>

                        {/* file field */}

                        <div className="mt-3">
                        <label for="image"> Select Post banner</label>
                            <Input id='image' type='file' multiple onChange={handleFileChange}/>
                        </div>
                        <div className="my-3">            
                        <Label for="category">Post Category </Label>
                        <Input 
                           type="select" 
                            id="category"
                            placeholder='Enter here'
                            className='rounded-0' 
                            name="categoryId"
                            value={post.category}
                            onChange={fieldChange}   
                            defaultValue={0}                    
                        >
                        <option disabled value={0}>--Select category--</option>
                       {
                        categories.map((category)=>(
                            <option value={category.categoryId} key={category.categoryId}>
                            {category.categoryTitle}
                        </option>                        
                        ))
                       }
                      </Input>
                        </div>
                        <Container className='text-center'>
                            <Button type="submit" className='rounded-0' color='primary'>Create Post</Button>
                            <Button className='rounded-0 ms-2' color='danger'>Reset</Button>
                        </Container>
                    </Form>
            </CardBody>
        </Card>
    </div>
    </div>
   ) 
}
export default AddPost;