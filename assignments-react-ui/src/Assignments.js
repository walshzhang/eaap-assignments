import React, {useEffect, useState} from 'react';
import Link from '@material-ui/core/Link';
import {makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Checkbox from "@material-ui/core/Checkbox";
import Paper from "@material-ui/core/Paper";
import InputBase from "@material-ui/core/InputBase";
import IconButton from "@material-ui/core/IconButton";
import SearchIcon from "@material-ui/icons/Search";
import Button from "@material-ui/core/Button";
import Grid from "@material-ui/core/Grid";
import ButtonGroup from "@material-ui/core/ButtonGroup";
import {Link as RouteLink} from 'react-router-dom'
import Snackbar from "@material-ui/core/Snackbar";
import MuiAlert from '@material-ui/lab/Alert';

const useStyles = makeStyles((theme) => ({
  seeMore: {
    marginTop: theme.spacing(3),
  },
  search: {
    padding: '2px 4px',
    display: 'flex',
    alignItems: 'center',
    width: 400,
  },
  toolbar: {
    display: 'flex',
    // flexDirection: 'column'
  },
  input: {
    marginLeft: theme.spacing(1),
    flex: 1
  },
  table: {
    marginTop: theme.spacing(1)
  },
  checkbox: {
    width: 60
  }
}));

function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

export default function Assignments() {
  const classes = useStyles();
  const [snack, setSnack] = useState({open: false, message: ''})

  const formatStatus = function (key) {
    const status = {created: '已创建', published: '已发布', removed: '已删除', closed: '已关闭', expired: '已过期'}
    return status[key.toLowerCase()];
  }

  const publish = function (assignment) {
    fetch(`http://localhost:19999/api/assignments/publish/${assignment.id}`, {
      method: 'put'
    }).then(response => {
      setSnack({...snack, open: true, level: 'success', message: '发布成功'})
    })
      .catch(error => {
        setSnack({...snack, level: 'error', open: true, message: '发布失败'})
      })
  }

  const [assignments, setAssignments] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch("http://localhost:19999/api/assignments");
      const assignments = await response.json();
      setAssignments(assignments);
    }

    fetchData()
  }, [snack])

  return (
    <React.Fragment>
      <Grid container spacing={1}>
        <Grid item>
          <Paper component={'form'} className={classes.search}>
            <InputBase className={classes.input} placeholder={'请输入标题'}/>
            <IconButton type={'submit'} aria-label={'search'}>
              <SearchIcon/>
            </IconButton>
          </Paper>
        </Grid>
        <Grid item style={{display: 'flex'}}>
          <Button color={'primary'} variant={'contained'} component={RouteLink} to={'/assignments/new'}>创建</Button>
        </Grid>
      </Grid>
      <Paper>
        <Table className={classes.table}>
          <TableHead>
            <TableRow>
              <TableCell className={classes.checkbox}>
                <Checkbox/>
              </TableCell>
              <TableCell>标题</TableCell>
              <TableCell>截止日期</TableCell>
              <TableCell>状态</TableCell>
              <TableCell>是否提交</TableCell>
              <TableCell>操作</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {assignments.map((assignment) => (
              <TableRow key={assignment.id}>
                <TableCell>
                  <Checkbox/>
                </TableCell>
                <TableCell>
                  <Link href={`http://localhost:8080/api/assignments/${assignment.id}`}>
                    {assignment.title}
                  </Link>
                </TableCell>
                <TableCell>{assignment.deadline}</TableCell>
                <TableCell>{formatStatus(assignment.status)}</TableCell>
                <TableCell>是</TableCell>
                <TableCell>
                  <ButtonGroup size={'small'}>
                    <Button>编辑</Button>
                    <Button>关闭</Button>
                    <Button onClick={() => publish(assignment)}>发布</Button>
                    <Button>查看完成情况</Button>
                    <Button>写作业</Button>
                  </ButtonGroup>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>
      <Snackbar anchorOrigin={{vertical: 'top', horizontal: 'center'}}
                onClose={() => setSnack({...snack, open: false})}
                autoHideDuration={3000}
                open={snack.open}>
        <Alert severity={snack.level} onClose={() => setSnack({...snack, open: false})}>
          {snack.message}
        </Alert>
      </Snackbar>
    </React.Fragment>
  );
}
