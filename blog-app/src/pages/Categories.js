import React,{useEffect,useState} from 'react'
import { useParams } from 'react-router-dom'
import { toast } from 'react-toastify';
import { Col, Container, Row } from "reactstrap";
import Base from '../components/Base';
import CategorySideMenu from '../components/CategorySideMenu';
import Post from '../components/Post';
import { loadPostCategoryWise } from '../services/post_services';
function Categories() {
    const {categoryId}=useParams();
    const [posts, setPosts] = useState([])

    useEffect(() => {
      console.log(categoryId);
      loadPostCategoryWise(categoryId).then(data=>{
               
        setPosts([...data])
      }).catch(error=>{
        console.log(error);
        toast.error("error in loading");
        
      })
      
    }, [categoryId])
  return (
    <Base>
        <Container className="mt-3">
    <Row>
        <Col md={2} className="border pt-5">
        <CategorySideMenu />
        </Col>
       <Col md={10}>
       <h1>Blogs Count({posts.length})</h1>
        {
            posts && posts.map((post,index) =>{
                return (
                    <Post key={index} post={post} />
                )
            })
        }
       </Col>
       </Row>
    </Container>
    </Base>
  )
}

export default Categories