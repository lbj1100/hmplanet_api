import request from '@/utils/request'

// 查询用户信息列表
export function listUsers(query) {
  return request({
    url: '/planet/users/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getUsers(userId) {
  return request({
    url: '/planet/users/' + userId,
    method: 'get'
  })
}

// 新增用户信息
export function addUsers(data) {
  return request({
    url: '/planet/users',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateUsers(data) {
  return request({
    url: '/planet/users',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delUsers(userId) {
  return request({
    url: '/planet/users/' + userId,
    method: 'delete'
  })
}
