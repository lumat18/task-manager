import axios from 'axios';

const baseUrl = 'http://localhost:8080/';
const apiClient = axios.create({
  baseURL: baseUrl,
});

export default {
  fetchIssues() {
    return apiClient.get('column');
  },
  updateColumn(column) {
    return apiClient.put('/column', column);
  },
  deleteIssue(issueId) {
    console.log('delete issue ', issueId);
    return apiClient.delete(`/issue/${issueId}`);
  },
};
