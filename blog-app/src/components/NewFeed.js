import {React,useEffect,useState} from 'react'
import { Col, Row,Pagination,PaginationItem,PaginationLink, Container } from 'reactstrap'
import { toast } from "react-toastify";
import { loadAllPosts } from "../services/post_services";
import Post from './Post';
import InfiniteScroll from 'react-infinite-scroll-component';
function NewFeed() {
    
    const[postContent , setPostContent]=useState({
        content:[],
        totalElements:'',
        totalPages:'',
        pageSize:'',
        lastPages:false,
        pageNumber:''
    })
    const [currentPage, setCurrentPage] = useState(0)
    useEffect(() => {    
          changePage(currentPage)
        
    }, [currentPage])
    // console.log(postContent.content.length)
    const changePage=(pageNumber=0,pageSize=5)=>{
       if(pageNumber>postContent.pageNumber && postContent.lastPages){
        return
       }
       if(pageNumber<postContent.pageNumber && postContent.pageNumber==0){
        return
       }
        loadAllPosts(pageNumber,pageSize).then(data=>{
           
            setPostContent({
                content:[...postContent.content,...data.content],
                totalElements:data.totalPages,
                totalPages:data.totalElements,
                pageSize:data.pageSize,
                lastPages:data.lastPages,
                pageNumber:data.pageNumber
            })
           // console.log(data);
            
        }).catch(error=>{
          toast.error("Error in loading posts")
        })
    }
    const changePageInfinite=()=>{
        console.log("paged changed")
        setCurrentPage(currentPage+1)
    }
   
  return (
    <div className="container-fluid">
     <Row>
        <Col md={{size:12}} >
     <h1>Blogs Count ({postContent?.totalElements})</h1>
       <InfiniteScroll      
       dataLength={postContent.content.length}
       next={changePageInfinite}
       hasMore={true}
       >
       {postContent.content.map((post)=>(
            <Post post={post} key={post.postId} />
            ))
        }
       </InfiniteScroll>
       {/* <Container className='text-center mt-3'>
       <Pagination size="lg">
            <PaginationItem  onClick={()=>changePage(postContent.pageNumber-1)} disabled={postContent.pageNumber===0}>
                <PaginationLink previous >
                Previous
                </PaginationLink>
            </PaginationItem>
            {
                [...Array(postContent.totalPages)].map((item,index) =>
                <PaginationItem onClick={()=>changePage(index)} key={index} active={index===postContent.pageNumber} >
                <PaginationLink  >
                {index+1}
                </PaginationLink>
            </PaginationItem>
                )
            }
            <PaginationItem disabled={postContent.lastPages} onClick={()=>changePage(postContent.pageNumber+1)}> 
                <PaginationLink next  >
                Next
                </PaginationLink>
            </PaginationItem>
        </Pagination>
  
       </Container> */}
        </Col>
     </Row>
    </div>
  )
}

export default NewFeed