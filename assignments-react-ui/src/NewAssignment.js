import React from 'react'
import TextField from "@material-ui/core/TextField";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";

function addAssignment(data) {
  data = {
    title: "string",
    description: "string",
    deadline: "2020-04-20 08:32:23"
  }

  return fetch('http://localhost:8080/api/assignments', {
    method: 'post',
    headers: {
      'content-type': 'application/json'
    },
    body: JSON.stringify(data)
  })
}

export default function NewAssignment() {
  return (
    <Card>
      <CardContent>
        <TextField margin={'normal'}
                   variant={'outlined'}
                   fullWidth
                   label={'标题'}/>
        <TextField margin={'normal'}
                   variant={'outlined'}
                   label={'截止日期'}
                   type={'datetime-local'}
                   fullWidth
                   InputLabelProps={{
                     shrink: true,
                   }}
                   inputProps={{
                     step: 300, // 5 min
                   }}
        />
        <TextField margin={'normal'}
                   variant={'outlined'}
                   fullWidth
                   multiline
                   rows={10}
                   label={'描述'}/>
        <Button variant={'contained'} type={'submit'} color={'primary'} onClick={addAssignment}>添加</Button>
      </CardContent>
    </Card>
  )
}
