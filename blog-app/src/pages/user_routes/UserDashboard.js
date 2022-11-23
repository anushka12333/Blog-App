import React,{useState,useEffect} from 'react';
import AddPost from '../../components/AddPost';
import Base from '../../components/Base'
import {Container} from 'reactstrap'
import { getCurrentUSerdetail } from '../../auth';
import { ServicePostDelete, loadPostUserWise } from '../../services/post_services';
import { toast } from 'react-toastify';
import Post from '../../components/Post';
const UserDashboard=()=>{
    const [user, setUser] = useState([])
    const [posts, setPosts] = useState([])
    useEffect(() => {
        console.log(getCurrentUSerdetail());        
       setUser(getCurrentUSerdetail())
      loadPostData()
    }, []) 
  
  function loadPostData(){
    loadPostUserWise(getCurrentUSerdetail().id).then(data=>{
        console.log(data);
        setPosts([...data].reverse());
        
    }).catch(error=>{
        console.log(error);
        toast.error("error in loading popst")       
    })
  }

    
    //delete function post
function deletePost(post){
  
    ServicePostDelete(post.postId).then(res=>{
        console.log(res);
        toast.success("post is deleted")
        loadPostData();
    }).catch(error=>{
        console.log(error);        
       toast('error in delete');
        
    })
    
}
   
    return(
   <Base>
    <Container>
        <AddPost />
        <h1 className='my-3'>Posts Count : ({posts.length})</h1>
        {posts.map((post,index)=>{
            return (
                <Post post={post} key={index} deletePost={deletePost} />
            )
        })}
    </Container>
   </Base>
    )
}
export default UserDashboard;