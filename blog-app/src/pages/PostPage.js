import { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { Card, CardBody, CardText, Col, Container, Row ,Button,Input} from "reactstrap";
import Base from "../components/Base";
import { BASE_URL } from "../services/helper";
import { createComment, loadPost } from "../services/post_services";

const PostPage=()=>{
   const {postId}= useParams()
   const [post, setPost] = useState(null)
   const [comment, setComment] = useState({
    content:''
   })
   useEffect(() => {
    //load post of postId
    loadPost(postId).then(data=>{
        console.log(data);
        setPost(data);
    }).catch(error=>{
        console.log(error)
        toast.error("Error in loading")
    })
   }, [])
   const printDate=(numbers)=>{
    return new Date(numbers).toLocaleDateString();
   }
   const submitPost=()=>{
    if(comment.content.trim()===''){
        return
    }
    createComment(comment,post.postId)
    .then(data=>{
        console.log(data)
        
        toast.success("comment added")
        setPost({
            ...post,
            comments:[...post.comments,data.data]
        })
        setComment({
            content:' '
        })
    }).catch(error=>{
        console.log(error)
    })
   }
    return (
       <Base >
        <Container>
            <Link to="/">Home</Link>/ {post &&(<Link to="">{post.title}</Link>)}
            <Row>
                <Col md={{
                    size:12
                }}>
                <Card className="mt-4 ps-2">
                    {
                        (post) && (
                            <CardBody>
                        <CardText className="mt-3">
                        Posted By <b>{post.user.name}</b> on <b>{printDate(post.addeddate)}</b> 
                        </CardText>
                        <CardText>
                            <span className="text-muted">{post.category.categoryTitle}</span>
                        </CardText>
                        <div className="divider" style={{
                            width:"100%",
                            height:"1px",
                            background:"#e2e2e2"
                        }}>

                        </div>
                        
                        <div className="image-container mt-3 shadow " style={{width:"30%"}}>
                        <img  className="img-fluid" src={BASE_URL+"/post/image/"+post.imageName} alt="" />
                        </div>
                        <CardText className="mt-5" dangerouslySetInnerHTML={{__html:post.content}}></CardText>
                    </CardBody>
                        )
                    }
                </Card>

                </Col>
            </Row>
            <Row>
                <Col md={
                    {size:9,
                    offset:1}
                }>
                <h3>Comments ({post ? post.comments.length :0})</h3>
                {
                    post && post.comments.map((c,index)=>(
                    <Card className="mt-2 border-0" key={index}>
                    <CardBody>
                        <CardText>
                            {c.content}
                        </CardText>
                    </CardBody>
                    </Card>
                    ))
                   
                }
                <Card className="mt-4 border-0" >
                    <CardBody>
                       
                       <Input
                        type="textarea" 
                        placeholder="Enter comment here"
                        value={comment.content}                       
                        onChange={(event)=>setComment({content:event.target.value})} />
                      <Button onClick={submitPost} className="
                      mt-4" color="primary"> Submit</Button>
                    </CardBody>
                    </Card>

                </Col>
            </Row>
        </Container>
       </Base>
    )
}
export default PostPage;