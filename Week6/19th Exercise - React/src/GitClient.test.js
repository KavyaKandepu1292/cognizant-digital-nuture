import axios from 'axios';
import GitClient from './GitClient';

jest.mock('axios');

describe('Git Client Tests', () => {

  test('should return repository names for octocat', async () => {
    const mockRepos = [
      { name: 'boysenberry-repo-1' },
      { name: 'git-consortium' },
      { name: 'hello-worId' }
    ];

    axios.get.mockResolvedValue({ data: mockRepos });

    const response = await GitClient.getRepositories('octocat');

    expect(axios.get).toHaveBeenCalledWith(
      'https://api.github.com/users/octocat/repos'
    );
    expect(response.data).toEqual(mockRepos);

    const repoNames = response.data.map((repo) => repo.name);
    expect(repoNames).toEqual([
      'boysenberry-repo-1',
      'git-consortium',
      'hello-worId'
    ]);
  });

});
